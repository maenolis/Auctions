package org.maenolis.auctions.services;

import java.io.StringWriter;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.maenolis.auctions.dao.Auction;
import org.maenolis.auctions.services.retObj.AuctionRetObject;

/**
 * The Class AdminService.
 */
@Path("/Admin")
public class AdminService {

	@GET
	@Path("/auctions")
	@Produces(MediaType.APPLICATION_XML)
	public Response getAll() throws JAXBException {

		List<AuctionRetObject> list = Auction.getAllAuctions();

		if (list.size() == 0) {
			return Response.status(200).entity("").build();
		}

		JAXBContext jaxbContext = JAXBContext
				.newInstance(AuctionRetObject.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		jaxbMarshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
		StringBuilder sb = new StringBuilder();
		StringWriter sw = new StringWriter();
		sb.append("<auctions>");
		for (AuctionRetObject auction : list) {
			jaxbMarshaller.marshal(auction, sw);
			sb.append(sw.toString());
			sw.flush();
		}

		System.out.println("Admin!!!" + " " + sw.toString());

		sb.append("</auctions>");
		String output = sb.toString();
		return Response.status(200).entity(output).build();
	}
}
