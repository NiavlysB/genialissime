package fr.um2.projetl3.tarotandroid.clients;

import static fr.um2.projetl3.tarotandroid.jeu.Context.P;
import static fr.um2.projetl3.tarotandroid.jeu.Context.D;

import java.util.Vector;

import org.keplerproject.luajava.LuaException;
import org.keplerproject.luajava.LuaState;
import org.keplerproject.luajava.LuaObject;
import org.keplerproject.luajava.LuaStateFactory;
import org.xmlpull.v1.XmlPullParser;

import android.util.Log;

import fr.um2.projetl3.tarotandroid.connection.Cartes;
import fr.um2.projetl3.tarotandroid.jeu.Carte;
import fr.um2.projetl3.tarotandroid.jeu.Contrat;
import fr.um2.projetl3.tarotandroid.jeu.Couleur;
import fr.um2.projetl3.tarotandroid.jeu.Main;

public class JoueurIA implements IJoueur
{
	
	private int pID;
	private Main pMain;
	private String nom;
	
	private LuaState L;
	
	public JoueurIA(String nom, XmlPullParser iaDefaut, int pID)
	{
		this.nom = nom;
		this.pID = pID;
		L = LuaStateFactory.newLuaState();
		L.openLibs();
		try {
			L.pushObjectValue(this);
		} catch (LuaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		L.setGlobal("javapi");
		try {
			XmlPullParser xpp = iaDefaut;
			while (xpp.getEventType()!=XmlPullParser.END_DOCUMENT) {
				if (xpp.getEventType()==XmlPullParser.START_TAG){
					if(xpp.getName().equals("luascript")){
						//s = L.getLuaObject("s").getString()+"\n"+xpp.getAttributeValue(0);
						L.LdoString(xpp.getAttributeValue(null, "lua"));
					} else {
						//s = xpp.getName()+" Echoué";
					}
				}
				xpp.next();
			}
		} catch (Throwable t) {
		}
	}
	
	public String popFluxus()
	{
		String s;
		
		try
		{
			L.LdoString("s = fluxus.pop()");
			s = L.getLuaObject("s").getString();
		}
		catch (Throwable t)
		{
			s = "Echec d'appel de Fluxus";
		}
		
		return s;
	}
	
	public String checkFluxus()
	{
		String s;
		
		try
		{
			L.LdoString("s = fluxus[1]");
			s = L.getLuaObject("s").getString();
		}
		catch (Throwable t)
		{
			s = "Echec d'appel de Fluxus";
		}
		
		return s;
	}
	
	public Boolean fluxusVide()
	{
		Boolean b;
		
		try
		{
			L.LdoString("b = fluxus.isEmpty()");
			b = L.getLuaObject("b").getBoolean();
		}
		catch (Throwable t)
		{
			b = true;
		}
		
		return b;
	}
	
	public void setID(int pID)
	{
		this.pID=pID;

	}

	public int getID()
	{
		return pID;
	}

	public void setMain(Main pMain)
	{
		//this.pMain = pMain;
	}


	public void addChienDansMain(Carte[] chien)
	{
		// LOOOOL
	}

	public void setNomDuJoueur(String s)
	{
		this.nom = "s";
	}

	public String getNomDuJoueur() 
	{
		// TODO Auto-generated method stub
		return this.nom;
	}

	public Contrat demanderAnnonce(Contrat contrat)
	{
		L.LdoString("cont = math.random(0,4)");
		int c = (int) L.getLuaObject("cont").getNumber();
		System.out.println(c);
		switch (c){
		case 0:
			return Contrat.PASSE;
		case 1:
			return Contrat.GARDE_SANS;
		case 2:
			return Contrat.GARDE_CONTRE;
		case 3:
			return Contrat.GARDE_SANS;
		case 4:
			return Contrat.GARDE_CONTRE;
		default:
			return Contrat.PASSE;
		}
	}

	public Carte[] demanderEcart()
	{
		// LOOOL
		return null;
	}

	public Carte demanderCarte()
	{
		int c;
		//this.pMain = D.getMain();
		//D.getMain().affiche();
		L.LdoString("tarot.main.clear()");
		Vector<Carte> vCartes = D.indiquerCartesLegalesJoueur();
		for (int i=0;i<vCartes.size();i++)
		{
			try {
				L.pushObjectValue(vCartes.elementAt(i).uid());
			} catch (LuaException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			L.setGlobal("input");
			L.LdoString("tarot.main.push(input)");
		}
		//L.LdoString("tarot.main.push(1) tarot.main.push(21) tarot.main.push(30) fluxus.push('Added cardz')");
		L.LdoString("c = tarot.main.rndGetCard()");
		c = (int) L.getLuaObject("c").getNumber();
		Carte ca = new Carte(c);
		//Log.d("played", ca.toString());
		//L.LdoString("fluxus.push('Flal')");
		//L.LdoString("tarot.main.push(1) tarot.main.push(21) tarot.main.push(30)");
		//L.LdoString("c = tarot.main.rndGetCard()");
		//L.LdoString("c = tarot.rndCard()");
		c = (int) L.getLuaObject("c").getNumber();
		return new Carte(c);
	}

	public Carte demanderRoi()
	{
		// TODO Auto-generated method stub
		return null;
	}

	public boolean possedeRoi(Carte roi)
	{
		// TODO Auto-generated method stub
		return false;
	}

	public String nom()
	{
		// TODO Auto-generated method stub
		return null;
	}

	public void direChien(Carte[] chien)
	{
		// TODO Auto-generated method stub
		
	}

	public void direCarteJouee(Carte c, String j)
	{
		// TODO Auto-generated method stub
		
	}

	public Carte demanderUneCartePourLecart() {
		// TODO Auto-generated method stub
		return null;
	}

	public void direAnnonce(Contrat c, String j)
	{
		// TODO Auto-generated method stub
		
	}

	public void direPliRemporté(Carte[] pli, String joueur)
	{
		// TODO Auto-generated method stub
		
	}

	public void recevoirMain(Cartes c) {
		// TODO Auto-generated method stub
		
	}

	public void recupererMain() {
		// TODO Auto-generated method stub
	}

	public void recupererPliEnCours() {
		// TODO Auto-generated method stub
	}

	public void recupererPliPrecedent() {
		// TODO Auto-generated method stub
	}

}
