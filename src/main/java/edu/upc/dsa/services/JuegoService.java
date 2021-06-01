package edu.upc.dsa.services;


import edu.upc.dsa.JuegoImpl;
import edu.upc.dsa.JuegoInterfaz;
import edu.upc.dsa.models.Usuario;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Api(value = "/user", description = "Endpoint to Track Service")
@Path("/user")
public class JuegoService {

    private JuegoInterfaz jm;

    public JuegoService() {
        this.jm = JuegoImpl.getInstance();
        if(jm.sizeUser()==0){
            this.jm.Registro("carlo@upc.edu","TheKiller99","Antonio","Miranda","dadacaefsa");
            this.jm.Registro("victor@upc.edu","Victory_777","Victor","Gutierrez","dadacaefsa");
            this.jm.Registro("toni@upc.edu","ToniMontana","Toni","Montana","dadacaefsa");
        }
    }
    //Obtener un usuario
    @GET
    @ApiOperation(value = "get a User", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Usuario.class),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Path("/{apodo}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@PathParam("apodo") String apodo) {
        Usuario u = this.jm.getUsuario(apodo);
        if (u == null) return Response.status(404).build();
        else  return Response.status(201).entity(u).build();
        //Track t = this.tm.getTrack(id);

    }
    //Crear un usuario
    @POST
    @ApiOperation(value = "create a new User", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response=Usuario.class),
            @ApiResponse(code = 500, message = "Validation Error")

    })

    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newUser(Usuario usuario) {

        if (usuario.getCorreo()==null || usuario.getPassword()==null || usuario.getApodo()==null )  return Response.status(500).entity(usuario).build();
        this.jm.Registro(usuario.getCorreo(), usuario.getApodo(), usuario.getNombre(), usuario.getApellido(), usuario.getPassword());

        return Response.status(201).entity(usuario).build();
    }
    //Actualizar un usuario
    @PUT
    @ApiOperation(value = "update a User", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Path("/")
    public Response updateUser(Usuario usuario) {

        Usuario u = this.jm.actualizarUsuario(usuario);

        if (u == null) return Response.status(404).build();

        return Response.status(201).build();
    }
    //Eliminar un usuario
    @DELETE
    @ApiOperation(value = "delete a User", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Path("/{apodo}")
    public Response deleteUser(@PathParam("apodo") String apodo) {
        Usuario t = this.jm.getUsuario(apodo);
        if (t == null) return Response.status(404).build();
        else this.jm.deleteUser(apodo);
        return Response.status(201).build();
    }
    //Login
    @POST
    @ApiOperation(value = "Login", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response=Usuario.class),
            @ApiResponse(code = 500, message = "Validation Error")

    })
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(Usuario usu) {

        Usuario u = this.jm.getUsuario(usu.getApodo());
        if (usu.getApodo() == null || usu.getPassword() == null){
            return Response.status(404).build();

        }
        else if(usu.getPassword().equals(u.getPassword())  ){
            return Response.status(201).entity(u).build();
        }
        else{
            return null;
        }

    }

    @GET
    @ApiOperation(value = "get badges", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = boolean.class, responseContainer = "List"),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Path("/{apodo}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBadges(@PathParam("apodo") String apodo) {
        boolean[] u = this.jm.getBadges(apodo);
        if (u == null) return Response.status(404).build();
        else  return Response.status(201).entity(u).build();
        //Track t = this.tm.getTrack(id);

    }

    /*@GET
    @ApiOperation(value = "get all Track", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Track.class, responseContainer="List"),
    })
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTracks() {

        List<Track> tracks = this.tm.findAll();

        GenericEntity<List<Track>> entity = new GenericEntity<List<Track>>(tracks) {};
        return Response.status(201).entity(entity).build()  ;

    }

    @GET
    @ApiOperation(value = "get a Track", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Track.class),
            @ApiResponse(code = 404, message = "Track not found")
    })
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTrack(@PathParam("id") String id) {
        Track t = this.tm.getTrack(id);
        if (t == null) return Response.status(404).build();
        else  return Response.status(201).entity(t).build();
    }

    @DELETE
    @ApiOperation(value = "delete a Track", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "Track not found")
    })
    @Path("/{id}")
    public Response deleteTrack(@PathParam("id") String id) {
        Track t = this.tm.getTrack(id);
        if (t == null) return Response.status(404).build();
        else this.tm.deleteTrack(id);
        return Response.status(201).build();
    }

    @PUT
    @ApiOperation(value = "update a Track", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "Track not found")
    })
    @Path("/")
    public Response updateTrack(Track track) {

        Track t = this.tm.updateTrack(track);

        if (t == null) return Response.status(404).build();

        return Response.status(201).build();
    }



    @POST
    @ApiOperation(value = "create a new Track", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response=Track.class),
            @ApiResponse(code = 500, message = "Validation Error")

    })

    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newTrack(Track track) {

        if (track.getSinger()==null || track.getTitle()==null)  return Response.status(500).entity(track).build();
        this.tm.addTrack(track);
        return Response.status(201).entity(track).build();
    }*/

}