package net.unesc.tcc.gabriel.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-05-08T00:24:12.603-0300")
@StaticMetamodel(Dispositivo.class)
public class Dispositivo_ {
	public static volatile SingularAttribute<Dispositivo, Integer> cd_dispositivo;
	public static volatile SingularAttribute<Dispositivo, String> ds_dispositivo;
	public static volatile SingularAttribute<Dispositivo, String> marca;
	public static volatile SingularAttribute<Dispositivo, String> firmware;
	public static volatile SingularAttribute<Dispositivo, String> ds_coordenadas;
	public static volatile SingularAttribute<Dispositivo, Date> dt_ultima_consulta;
	public static volatile SingularAttribute<Dispositivo, Date> dt_ultima_online;
	public static volatile SingularAttribute<Dispositivo, Boolean> online;
}
