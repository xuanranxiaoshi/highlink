package info.nemoworks.highlink.sink;

/**
 * @description:
 * @author：jimi
 * @date: 2024/3/25
 * @Copyright：
 */

import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.introspect.Annotated;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;

public class WriteOnlyAnnotationIntrospector extends JacksonAnnotationIntrospector {
    @Override
    public JsonProperty.Access findPropertyAccess(Annotated m) {
        JsonProperty.Access access = super.findPropertyAccess(m);
        if (access == null) {
            // 如果没有指定 access，则默认设置为 WRITE_ONLY
            return JsonProperty.Access.WRITE_ONLY;
        }
        return access;
    }
}

