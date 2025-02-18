package fiap.logistics.api.controllers;

import fiap.logistics.api.dto.*;
import fiap.logistics.application.usecases.entregadores.EntregadorDisponivelUseCase;

import fiap.logistics.domain.model.DeliveryManDomain;
import fiap.logistics.application.usecases.entregadores.DeliveryManUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/deliveryman")
@Tag(name = "User API", description = "Classe responsavel por gerenciar entregadores")
public class EntregadorController {

    private final DeliveryManUseCase deliveryManUseCase;
    private final EntregadorDisponivelUseCase entregadorDisponivelUseCase;

    public EntregadorController(DeliveryManUseCase deliveryManUseCase, EntregadorDisponivelUseCase entregadorDisponivelUseCase) {
        this.deliveryManUseCase = deliveryManUseCase;
        this.entregadorDisponivelUseCase = entregadorDisponivelUseCase;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    @Operation(summary = "Cadastra entregadores.", description = "Cadastra um entregador interessado em fazer entregas.")
    @ApiResponse(responseCode = "201", description = "Entregador salvo com sucesso")
    @Schema(name = "DeliveryManDTO", implementation = DeliveryManDTO.class)
    public String saveDeliveryManUseCase(@RequestBody DeliveryManDTO deliveryManDTO) {
        deliveryManUseCase.saveDeliveryMan(DeliveryManDomain.builder().name(deliveryManDTO.getName()).build());
        return "DeliveryMan saved";
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retorna um Entregador cadastrado.")
    @ApiResponse(responseCode = "200", description = "Entregador retornado com sucesso")
    public ResponseEntity<DeliveryManDTO> getDeliveryMan(@PathVariable Long id) {
        return ResponseEntity.ok(deliveryManUseCase.getDeliveryMan(id));
    }

    @GetMapping
    @Operation(summary = "Retorna uma lista de Entregadores cadastrados.")
    @ApiResponse(responseCode = "200", description = "Entregadores retornados com sucesso")
    public ResponseEntity<List<DeliveryManDTO>> getAllDeliveryMan() {
        return ResponseEntity.ok(deliveryManUseCase.getAllDeliveryMan());
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping("/entregador-disponivel/{idEntregador}")
    @Operation(summary = "Inclui um Entregador cadastrado na lista de entregadores habilitados para fazer entregas.")
    @ApiResponse(responseCode = "204", description = "Entregador salvo com sucesso")
    public String salvarEntregadorDisponivel(@Parameter(description = "Id de um entregador ja cadastrado para entregas."
    , required = true) @PathVariable Long idEntregador) {

        entregadorDisponivelUseCase.salvarEntregador(idEntregador);

        return "Entregador salvo com sucesso";
    }


}
