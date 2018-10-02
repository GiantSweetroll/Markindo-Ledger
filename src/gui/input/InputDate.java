package gui.input;

import giantsweetroll.date.Date;
import giantsweetroll.date.DateSelectionPanel;
import methods.Methods;

public class InputDate extends FormElement
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 127241161623495404L;

	private DateSelectionPanel date;
	
	//Constructors
	public InputDate(String name)
	{
		super(name);
		
		//Initialization
		this.date = new DateSelectionPanel(true, "Auto", "Default", "Set tanggal ke tanggal sekarang", "Kembalikan tanggal pada sebelumnya");
		
		//Properties
		Methods.autoLayout(this.getLayout(), this.date, this.getTitleLabel(), this);
		
		//Add to panel
		this.add(this.date);
	}
	
	//Methods
	public void setDate(Date date)
	{
		this.date.setDate(date);
	}
	public Date getDate()
	{
		return this.date.getDate();
	}

	//Overridden Methods
	@Override
	public void resetDefaults()
	{
		this.date.resetDefault();
	}

	@Override
	public void refresh(){}

	@Override
	public boolean isFilled() {return true;}

	@Deprecated
	@Override
	public String getData()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Deprecated
	@Override
	public void setData(String data) 
	{
		// TODO Auto-generated method stub	
	}
}
