package ticketreservation;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.StringUtils;

@Data
public class Ticket {
    private final String name;
    @EqualsAndHashCode.Exclude private boolean isReserved = false;
    @EqualsAndHashCode.Exclude private String client = StringUtils.EMPTY;
}
