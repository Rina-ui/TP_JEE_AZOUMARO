package glsi.api.tp_jee_egab.Service;

import org.iban4j.CountryCode;
import org.iban4j.Iban;
import org.springframework.stereotype.Service;

import java.util.Random;

import static java.lang.String.format;

@Service
class IbanService {

    private final Random random = new Random();

    public String generateTogoIban() {
        return new Iban.Builder()
                .countryCode(CountryCode.TG)
                .bankCode(generateNumericString(3))
                .branchCode(generateNumericString(5))
                .accountNumber(generateNumericString(11))
                .nationalCheckDigit(generateNumericString(2))
                .build()
                .toString();
    }

    private String generateNumericString(int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }

}
