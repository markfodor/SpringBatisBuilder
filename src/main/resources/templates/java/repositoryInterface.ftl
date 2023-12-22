/**
* ${comment}
*/

package ${model.packageName()};

import java.util.List;

public interface ${model.className()}Repository {

    int insert(${model.className()} ${model.className()});

    int update(${model.className()} ${model.className()});

    int delete(); // TODO handle id

    ${model.className()} findById(); // TODO handle id

    List<${model.className()}> findAll();
}