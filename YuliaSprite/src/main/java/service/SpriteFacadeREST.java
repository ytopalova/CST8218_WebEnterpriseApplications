/* 
*Author:    Yuliia Topalova - 040981104
*Project:   Assignment 1
*Class:     SpriteFacadeREST
*Purpose:   Handels all the RESTful requests by pulling the functions.
 */
package service;

import cst8218.topa0005.entity.Sprite;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Stateless
@Path("cst8218.topa0005.entity.sprite")
public class SpriteFacadeREST extends AbstractFacade<Sprite> {

    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager em;

    public SpriteFacadeREST() {
        super(Sprite.class);
    }

    /*
    Method update the Sprite with specific id with new changes
    If the id is null or doesn’t exist, or if the id in the 
    Sprite`s body of the request has a non-matching id.
     */
    @POST
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response create(@PathParam("id") Long id, Sprite entity) {
        if (entity.getId() == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else if (entity.getId().equals(id) == false) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            Sprite spriteValue = super.find(id);
            if (spriteValue != null) {
                spriteValue.updateSprite(entity);
                return Response.status(Response.Status.CREATED).entity(spriteValue).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        }
    }

    /*
    Method post Sprite and generate id if it is null. If id is not null and 
    existing, update the values. Other cases are invalid.
    */ 
    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response createAll(Sprite entity) {
        if (entity.getId() == null) {
            super.create(entity);
            return Response.status(Response.Status.CREATED).entity(entity).build();
        } else {
            Sprite oldValue = super.find(entity.getId());
            if (oldValue != null) {
                oldValue.updateSprite(entity);
                return Response.status(Response.Status.CREATED).entity(oldValue).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        }
    }

    /*
    Method updates the body of the Sprite with the specific id. 
    Return invalid if id in the body not matching or Sprite with 
    such id does not exist.
    */
    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response edit(@PathParam("id") Long id, Sprite entity) {
        if (entity.getId() == null || !(id.equals(entity.getId()))) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            Sprite sprite = super.find(id);
            if (sprite != null) {
                super.edit(entity);
                return Response.status(Response.Status.CREATED).entity(entity).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        }
    }

    //you can not put/update without specifing the id
    @PUT
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response editAll(Sprite entity) {
        return Response.status(Response.Status.BAD_REQUEST).entity(entity).build();
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Sprite find(@PathParam("id") Long id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Sprite> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Sprite> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
