package constants;

import java.util.ArrayList;
import java.util.List;

import datadriver.Alokasi;
import datadriver.PIC;
import datadriver.Program;
import datadriver.Site;
import datadriver.Stock;
import methods.FileOperation;

public class Globals 
{
	public static final List<PIC> PICS = new ArrayList<PIC>();
	public static final List<Program> PROGRAMS = new ArrayList<Program>();
	public static final List<Site> SITES = new ArrayList<Site>();
	public static final List<Stock> STOCKS = new ArrayList<Stock>();
	public static final List<Alokasi> ALOKASI = new ArrayList<Alokasi>();
	
	//Methods
	public static void reloadProgram()
	{
		Globals.PROGRAMS.clear();
		List<Program> programs = FileOperation.loadProgram();
		for (Program prog : programs)
		{
			Globals.PROGRAMS.add(prog);
		}
	}
	public static void reloadSite()
	{
		Globals.SITES.clear();
		List<Site> sites = FileOperation.loadSite();
		for (Site site : sites)
		{
			Globals.SITES.add(site);
		}
	}
	public static void reloadPIC()
	{
		Globals.PICS.clear();
		List<PIC> pics = FileOperation.loadPIC();
		for (PIC pic : pics)
		{
			Globals.PICS.add(pic);
		}
	}
	public static void reloadStock()
	{
		Globals.STOCKS.clear();
		List<Stock> stocks = FileOperation.loadStock();
		for (Stock stock : stocks)
		{
			Globals.STOCKS.add(stock);
		}
	}
	public static void reloadAlokasi()
	{
		Globals.ALOKASI.clear();
		List<Alokasi> alokasi = FileOperation.loadAlokasi();
		for (Alokasi alok : alokasi)
		{
			Globals.ALOKASI.add(alok);
		}
	}
	public static void reloadDataDrivers()
	{
		Globals.reloadAlokasi();
		Globals.reloadPIC();
		Globals.reloadProgram();
		Globals.reloadSite();
		Globals.reloadStock();
	}
}
