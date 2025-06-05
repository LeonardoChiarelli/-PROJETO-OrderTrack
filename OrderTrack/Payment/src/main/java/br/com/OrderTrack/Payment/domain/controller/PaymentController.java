package br.com.OrderTrack.Payment.domain.controller;

import br.com.OrderTrack.Payment.domain.dto.MakePaymentDTO;
import br.com.OrderTrack.Payment.domain.dto.PaymentDetailDTO;
import br.com.OrderTrack.Payment.domain.service.PaymentService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("orderTrack/payment")
public class PaymentController {

    @Autowired
    private PaymentService service;

    @Autowired
    private HttpServletRequest request;

    @PostMapping
    @Transactional
    public ResponseEntity<PaymentDetailDTO> realizePayment(@RequestBody @Valid MakePaymentDTO dto, UriComponentsBuilder builder /* DTO QUE RECEBE AS MENSAGENS */) {
        var payment = service.makePayment(dto);

        var uri = builder.path("/{id}").buildAndExpand(payment.getId()).toUri();

        return ResponseEntity.created(uri).body(new PaymentDetailDTO(payment));
    }

    @
}
