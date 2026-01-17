package glsi.api.tp_jee_egab.Service;

import org.iban4j.CountryCode;
import org.iban4j.Iban;
import org.springframework.stereotype.Service;

import java.util.Random;

import static java.lang.String.format;

@Service
class IbanService {

    private final Random random = new Random();

    public String generateIban(CountryCode countryCode) {
        return new Iban.Builder()
                .countryCode(countryCode)
                .bankCode(generateBankCode())
                .branchCode(generateBranchCode())
                .accountNumber(generateAccountNumber())
                .build()
                .toString();
    }

    public String generateFrenchIban() {
        return generateIban(CountryCode.FR);
    }

    public String generateTogoIban() {
        return generateIban(CountryCode.TG);
    }

    private String generateBankCode() {
        return String.format("%05d", random.nextInt(100000));
    }

    private String generateBranchCode() {
        return String.format("%05d", random.nextInt(100000));
    }

    private String generateAccountNumber() {
        return String.format("%011d", random.nextLong() & Long.MAX_VALUE).substring(0, 11);
    }

}
