package steamworks.codeManage.service.impl;

import lombok.AllArgsConstructor;
import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import steamworks.codeManage.domain.converter.CodeConverter;
import steamworks.codeManage.domain.model.CodeModel;
import steamworks.codeManage.domain.model.excpetion.InvalidCodeIdException;
import steamworks.codeManage.domain.model.excpetion.RecursiveParentException;
import steamworks.codeManage.domain.model.form.CreateCodeForm;
import steamworks.codeManage.domain.model.form.UpdateCodeForm;
import steamworks.codeManage.entity.Code;
import steamworks.codeManage.repository.CodeRepository;
import steamworks.codeManage.service.CodeService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
