package ${config.mapperPackage};

import ${config.entityPackage}.${entity.className};
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ${entity.className}Mapper extends BaseMapper<${entity.className}> {

}
