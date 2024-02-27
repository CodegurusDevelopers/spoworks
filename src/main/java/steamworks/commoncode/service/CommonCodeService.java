package steamworks.commoncode.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import steamworks.codeManage.domain.converter.CodeConverter;
import steamworks.codeManage.domain.model.CodeModel;
import steamworks.codeManage.domain.model.form.CreateCodeForm;
import steamworks.codeManage.entity.Code;
import steamworks.commoncode.domain.CommonCodeMappingModel;
import steamworks.commoncode.domain.CommonCodeModel;
import steamworks.commoncode.domain.CreateCommonCodeForm;
import steamworks.commoncode.entity.CommonCode;
import steamworks.commoncode.entity.CommonCodeMapping;
import steamworks.commoncode.repository.CommonCodeMappingRepository;
import steamworks.commoncode.repository.CommonCodeRepository;

import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;
@Slf4j
@Service
@AllArgsConstructor
public class CommonCodeService {

    private CommonCodeRepository commonCodeRepository;
    private CommonCodeMappingRepository commonCodeMappingRepository;
    private List<CommonCodeModel> getCommonCodes() {
        return commonCodeRepository.findAll().stream()
                .map(CommonCodeConverter::from)
                .collect(Collectors.toList());
    }

    public List<CommonCodeMappingModel> getCommonCodeMappings() {
        return commonCodeMappingRepository.findAll().stream()
                .map(CommonCodeConverter::fromMapping)
                .collect(Collectors.toList());
    }

    public List<CommonCodeModel> getAllCodes() {
        List<CommonCodeModel> commonCodes = getCommonCodes();

        List<CommonCodeMappingModel> commonCodeMappings = getCommonCodeMappings().stream()
                .sorted(
                        Comparator.comparing(CommonCodeMappingModel::getParentsId)
                                .thenComparing(CommonCodeMappingModel::getChildId)
                )
                .collect(Collectors.toList());

        Map<Long, CommonCodeModel> idMap = new HashMap<>();

        commonCodes.forEach(c -> idMap.put(c.getCommCdId(), c));

        commonCodeMappings.forEach(m -> {
            CommonCodeModel parent = idMap.get(m.getParentsId());
            CommonCodeModel child = idMap.get(m.getChildId());

            child.setHasParent(true);
            parent.add(child);
        });

        // 부모를 안가지는 애들이 최상위 리스트가 됨

        List<CommonCodeModel> root = commonCodes.stream()
                .filter(e -> !e.getHasParent())
                .collect(Collectors.toList())
                .stream()
                .sorted(Comparator.comparing(CommonCodeModel::getOrd))
                .collect(Collectors.toList());

        List<CommonCodeModel> total = new ArrayList<>();

        // 10depth까지만 출력되도록 수정
        cloneList(0, root, total);
        return total;
    }
    private void cloneList(int depth, List<CommonCodeModel> from, List<CommonCodeModel> to) {
        if(depth == 10) {
            return;
        }

        for(CommonCodeModel fromChild : from) {
            CommonCodeModel toChild = fromChild.clone();
            to.add(toChild);
            if(fromChild.getChildren() != null) {
                toChild.setChildren(new ArrayList<>());
                cloneList(depth + 1, fromChild.getChildren(), toChild.getChildren());
            }

        }
    }
    private Timestamp now() {
        return new Timestamp(new Date().getTime());
    }

    /**
     * creatorId는 jwt에서 도출.
     * @param form
     * @return
     */
    public CommonCodeModel createCommonCode(CreateCommonCodeForm form) {
        CommonCode newCode = CommonCode.builder()
                .cd(form.getCd())
                .cdNm(form.getCdNm())
                .cdEn(form.getCdEn())
                .cdJp(form.getCdJp())
                .cdCn(form.getCdCn())
                .activateYn(form.getActivateYn())
                .creatorId(form.getCreatorId())
                .createdDatentime(now())
                .ord(form.getOrd())
                .build();

        CommonCode commonCode = commonCodeRepository.save(newCode);
        List<CommonCodeMapping> mappings = new ArrayList<>();
        List<Long> list = form.getParents();
        int order = 1;
        for (Long l : list) {
            CommonCodeMapping commonCodeMapping = new CommonCodeMapping();
            commonCodeMapping.setCreatorId(form.getCreatorId());
            commonCodeMapping.setCreatedDatentime(now());
            commonCodeMapping.setOrd(order++);
            commonCodeMapping.setChildId(commonCode.getCommCdId());
            commonCodeMapping.setParentsId(l);
            mappings.add(commonCodeMapping);

        }

        commonCodeMappingRepository.saveAll(mappings);

        return CommonCodeConverter.from(commonCode);
    }

}
