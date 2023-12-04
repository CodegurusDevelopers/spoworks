package steamworks.codeManage.service.impl;

import lombok.AllArgsConstructor;
import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import steamworks.codeManage.domain.converter.CodeConverter;
import steamworks.codeManage.domain.model.CodeChildrenModel;
import steamworks.codeManage.domain.model.CodeModel;
import steamworks.codeManage.domain.model.excpetion.InvalidCodeIdException;
import steamworks.codeManage.domain.model.excpetion.RecursiveParentException;
import steamworks.codeManage.domain.model.form.CreateCodeForm;
import steamworks.codeManage.domain.model.form.UpdateCodeForm;
import steamworks.codeManage.entity.Code;
import steamworks.codeManage.repository.CodeRepository;
import steamworks.codeManage.service.CodeService;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CodeServiceImpl extends EgovAbstractServiceImpl implements CodeService {
    final CodeRepository codeRepository;

    @Override
    public List<CodeModel> getAllCodes() {
        List<CodeModel> codes = new ArrayList<>();
        codeRepository.findAll().forEach(code -> codes.add(CodeConverter.from(code)));

        return codes;
    }

    @Override
    public List<CodeChildrenModel> getChildrenHierarchyCodes() {
        List<CodeModel> codes = this.getAllCodes();
        List<CodeChildrenModel> codeChildrenModelList = new ArrayList<>();

        // 코드 리스트 중복 제거 로직
        /* code->parent 를 가지고 있는 구조에서 code - children으로 만드는 방법은 아래와 같다.
         * 1. codeChildrenModel 안에 code 를 전부 넣어준다.
         * 2. 이때 넣어준 code 는 reference 형태로 주소값으로만 갖고 있으니 객체를 변동해도 객체에 대응하는 인스턴스가 대응한다.
         * 3. 기존 code->parent 구조는 code, code->children 구조는 codeC 로 명칭한다.
         * 4. code 에서 parent 를 갖지 않는 경우는 최상위이므로 무시한다.
         * 5. code 에서 parent 를 갖는 경우 codeC 리스트에서 codeC를 찾아 children 에 넣어준다.
         *       * 이때 codeC children 에 들어가므로 codeC.children 하위에 있는 codeC 에도 자동으로 들어간다 (위 설명 2번 참조)
         * 6. 마지막으로 parent 를 갖고 있지 않은 codeC들을 다 필터링해주면 하이라키 구조가 표현된 codeChildrenModel이 만들어진다.
         */
        for(CodeModel code : codes) codeChildrenModelList.add(new CodeChildrenModel(code));
        for(int i = 0 ; i < codes.size(); i++) {
            CodeModel code = codes.get(i);
            if(code.getParents().size() == 0) continue;
            for(CodeModel parent :code.getParents() ) {
                Optional<CodeChildrenModel> parentModel = codeChildrenModelList.stream()
                        .filter(codeChildrenModel -> parent.getId().equals(codeChildrenModel.getId())).findAny();
                if(parentModel.isEmpty()) continue; // TODO: exception error;
                parentModel.get().getChildren().add(codeChildrenModelList.get(i));
            }
        }

        for(int i = 0 ; i < codes.size(); i++) {
            CodeModel code = codes.get(i);
            if(code.getParents().size() != 0)  codeChildrenModelList.set(i, null);
        }

        return codeChildrenModelList.stream().filter(Objects::nonNull).collect(Collectors.toList());
    }

    @Override
    public CodeModel createCode(CreateCodeForm codeForm) {
        Code newCode = Code.builder()
                .name(codeForm.getName())
                .displayName(codeForm.getDisplayName())
                .active(codeForm.getActive())
                .orderType(codeForm.getOrderType())
                .build();

        List<Code> parentCodes = new ArrayList<>();
        codeRepository.findAllById(codeForm.getParents()).forEach(parentCodes::add);
        newCode.setParents(parentCodes);

        return CodeConverter.from(codeRepository.save(newCode));
    }

    @Override
    @Transactional
    public CodeModel updateCode(UpdateCodeForm codeForm) {
        Optional<Code> updatedCode = codeRepository.findById(codeForm.getId());
        if(updatedCode.isEmpty()) throw new InvalidCodeIdException();

        updatedCode.get().setExternalCodes(codeForm.getExternalCodes());
        updatedCode.get().setActive(codeForm.getActive());
        updatedCode.get().setOrderType(codeForm.getOrderType());

        List<Code> parentCodes = new ArrayList<>();
        codeRepository.findAllById(codeForm.getParents()).forEach(parentCodes::add);
        checkRecursiveParent(updatedCode.get(), parentCodes);
        updatedCode.get().setParents(parentCodes);

        return CodeConverter.from(codeRepository.save(updatedCode.get()));
    }

    @Override
    @Transactional
    public void deleteCode(Long id) {
        Optional<Code> code = codeRepository.findById(id);
        if(code.isEmpty()) throw new InvalidCodeIdException();

        for (Code c : codeRepository.findByParents(code.get())) {
            c.getParents().remove(code.get());
        }

        codeRepository.deleteById(id);
    }

    private void checkRecursiveParent(final Code target, final List<Code> parents) {
        for(Code parent : parents) {
            if(hasRecursiveParentIssue(target, parent)) throw new RecursiveParentException(parent);
        }
    }

    private boolean hasRecursiveParentIssue(Code target, Code parent) {
        if(target.getId().equals(parent.getId())) return true;
        List<Code> parents = parent.getParents();
        for(Code p : parents) {
            boolean recursiveCheck = hasRecursiveParentIssue(target, p);
            if(recursiveCheck) return true;
        }

        return false;
    }
}
