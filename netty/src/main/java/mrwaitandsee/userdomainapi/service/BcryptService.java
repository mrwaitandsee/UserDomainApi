package mrwaitandsee.userdomainapi.service;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class BcryptService {
    private final int rounds;

    public BcryptService() {
        rounds = 8;
    }

    public String hash(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(rounds));
    }

    public boolean verifyHash(String password, String hash) {
        return BCrypt.checkpw(password, hash);
    }
}
