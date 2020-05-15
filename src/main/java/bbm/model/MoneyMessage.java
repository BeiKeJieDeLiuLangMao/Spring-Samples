package bbm.model;

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
public class MoneyMessage {
    private int userId;
    private int count;
}
