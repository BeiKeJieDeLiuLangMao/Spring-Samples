package bbm.model;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author bbm
 * @date 2020/5/14
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class RestData implements Serializable {
    private static final long serialVersionUID = -6175197797440818644L;
    private int id;
    private String value;
}
