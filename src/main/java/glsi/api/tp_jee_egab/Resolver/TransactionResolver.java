package glsi.api.tp_jee_egab.Resolver;

import glsi.api.tp_jee_egab.DTO.TransactionDTO;
import glsi.api.tp_jee_egab.Service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
class TransactionResolver {

    private final TransactionService transactionService;

    @MutationMapping
    TransactionDTO makeTransaction(@RequestBody TransactionDTO transactionDTO) {
        return transactionDTO;
    }

}
