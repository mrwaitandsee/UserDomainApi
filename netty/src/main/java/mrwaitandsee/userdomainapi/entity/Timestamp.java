package mrwaitandsee.userdomainapi.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class Timestamp implements Serializable {
    private LocalDateTime timestamp;
}
