/*
 * This file is generated by jOOQ.
 */
package bbm.jooq.tables.pojos;


import java.io.Serializable;

import javax.annotation.Generated;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.12.4"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Test implements Serializable {

    private static final long serialVersionUID = -2139157387;

    private Integer id;
    private String  value;

    public Test() {}

    public Test(Test value) {
        this.id = value.id;
        this.value = value.value;
    }

    public Test(
        Integer id,
        String  value
    ) {
        this.id = id;
        this.value = value;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Test (");

        sb.append(id);
        sb.append(", ").append(value);

        sb.append(")");
        return sb.toString();
    }
}
