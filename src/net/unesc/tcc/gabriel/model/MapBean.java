package net.unesc.tcc.gabriel.model;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

@ManagedBean
@ViewScoped
public class MapBean implements Serializable {
	
	/**
	 * 
	 */
	
	private static final long serialVersionUID = -453995987121265610L;

	private MapModel advancedModel;

	private Marker marker;

	public MapBean() {
		advancedModel = new DefaultMapModel();
		
		//Shared coordinates
		LatLng coord1 = new LatLng(-28.6749125,-49.3630053);
		LatLng coord2 = new LatLng(-28.6757785,-49.3650867);
		LatLng coord3 = new LatLng(-28.677191,-49.367789);
		LatLng coord4 = new LatLng(-28.6777746,-49.3684448);
		
		//Icons and Data
		advancedModel.addOverlay(new Marker(coord1, "BOI01", "konyaalti.png", "http://maps.google.com/mapfiles/ms/micons/blue-dot.png"));
		advancedModel.addOverlay(new Marker(coord2, "BOI02", "ataturkparki.png", "http://maps.google.com/mapfiles/ms/micons/red-dot.png"));
		advancedModel.addOverlay(new Marker(coord4, "BOI03", "kaleici.png", "http://maps.google.com/mapfiles/ms/micons/pink-dot.png"));
		advancedModel.addOverlay(new Marker(coord3, "BOI04", "karaalioglu.png", "http://maps.google.com/mapfiles/ms/micons/yellow-dot.png"));
	}

	public MapModel getAdvancedModel() {
		return advancedModel;
	}
	
	public void onMarkerSelect(OverlaySelectEvent event) {
		marker = (Marker) event.getOverlay();
	}
	
	public Marker getMarker() {
		return marker;
	}
}
					