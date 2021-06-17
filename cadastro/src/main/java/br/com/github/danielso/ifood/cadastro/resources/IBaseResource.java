package br.com.github.danielso.ifood.cadastro.resources;

import java.io.Serializable;
import java.util.List;

import javax.validation.Valid;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

import br.com.github.danielso.ifood.cadastro.converter.ConverterDTO;
import br.com.github.danielso.ifood.cadastro.dto.BaseDTO;
import br.com.github.danielso.ifood.cadastro.entities.BaseEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

public interface IBaseResource <E extends BaseEntity, D extends BaseDTO, K extends Serializable, R extends PanacheRepositoryBase<E, K>> {

    @GET
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Registros listados com sucesso"),
            @APIResponse(responseCode = "400", description = "Erro na obtenção dos dados ou filtro"),
            @APIResponse(responseCode = "500", description = "Erro interno do servidor")})
    List<D> getAll(@QueryParam("sort") @DefaultValue("id") String sortField, @QueryParam("order") @DefaultValue("asc") String order);
    
    @POST
    @APIResponses(value = {
            @APIResponse(responseCode = "204", description = "Registro criado com sucesso"),
            @APIResponse(responseCode = "404", description = "Não foi possível cadastrar o registro."),
            @APIResponse(responseCode = "500", description = "Erro interno do servidor") })
    void save(@Valid D dto);

    @PUT
    @Path("{id}")
    @APIResponses(value = {
            @APIResponse(responseCode = "204", description = "Registro atualizado com sucesso"),
            @APIResponse(responseCode = "404", description = "Registro não encontrado."),
            @APIResponse(responseCode = "500", description = "Erro interno do servidor") })
    void update(@PathParam("id") K id, @Valid D dto);

    @GET
	@Path("{id}")
	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "Registro carregado com sucesso."),
			@APIResponse(responseCode = "404", description = "Registro não encontrado."),
			@APIResponse(responseCode = "500", description = "Erro interno do servidor") })
	D getById(@PathParam("id") K id);

    @DELETE
    @Path("{id}")
    @APIResponses(value = {
            @APIResponse(responseCode = "204", description = "Registro deletado com sucesso"),
            @APIResponse(responseCode = "404", description = "Registro não encontrado."),
            @APIResponse(responseCode = "500", description = "Erro interno do servidor")})
    void delete(@PathParam("id") K id);

    R getRepository();

    ConverterDTO<E, D> getConverter();

}
