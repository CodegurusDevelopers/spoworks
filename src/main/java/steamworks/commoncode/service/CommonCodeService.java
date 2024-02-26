package steamworks.commoncode.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import steamworks.commoncode.domain.CommonCodeMappingModel;
import steamworks.commoncode.domain.CommonCodeModel;
import steamworks.commoncode.repository.CommonCodeMappingRepository;
import steamworks.commoncode.repository.CommonCodeRepository;

import java.util.*;
import java.util.stream.Collectors;

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
                .sorted(Comparator.comparing(CommonCodeModel::getOrder))
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
}
