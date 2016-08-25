package com.box.sdk;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Type of resource (e.g.: folder, file, comment, etc...).
 *
 * @since 2.2.1
 *
 */
@Documented
@Inherited
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface BoxResourceType {

    // Bug in checkstyle: Unused Javadoc tag.
    // TODO checkstyle: need re-evaluation after checkstyle update
    //CHECKSTYLE:OFF
    /**
     * @return Type of resource (e.g.: folder, file, comment, etc...).
     */
    //CHECKSTYLE:ON
    String value();

}
