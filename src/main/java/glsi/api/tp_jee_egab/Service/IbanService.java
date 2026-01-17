package glsi.api.tp_jee_egab.Service;

import org.iban4j.CountryCode;
import org.iban4j.Iban;
import org.springframework.stereotype.Service;

import java.util.Random;

import static java.lang.String.format;

@Service
class IbanService {

    private final Random random = new Random();

    public String generateFrenchIban() {
        return new Iban.Builder()
                .countryCode(CountryCode.FR)
                .bankCode(generateNumericString(5))      // 5 chiffres
                .branchCode(generateNumericString(5))    // 5 chiffres
                .accountNumber(generateNumericString(11)) // 11 chiffres
                .nationalCheckDigit(generateNumericString(2)) // 2 chiffres clé RIB
                .build()
                .toString();
    }

    public String generateTogoIban() {
        return new Iban.Builder()
                .countryCode(CountryCode.TG)
                .bankCode(generateNumericString(2))
                .branchCode(generateNumericString(2))
                .accountNumber(generateNumericString(16))
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
