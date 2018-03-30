package ${config.serviceImplPackage};

import ${config.entityPackage}.${entity.className};
import ${config.mapperPackage}.${entity.className}Mapper;
import ${config.servicePackage}.${entity.className}Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ${entity.className}ServiceImpl extends ServiceImpl<${entity.className}Mapper,${entity.className}> implements ${entity.className}Service {

}
