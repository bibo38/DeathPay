package me.bibo38.DeathPay;

import java.io.File;
import java.io.InputStream;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class CustomConfig
{
	private FileConfiguration customCfg = null;
	private File customCfgFile = null;
	private DeathPay main;
	private String cfgDefaultName = null;
	
	private void construct(File file, DeathPay emain)
	{
		customCfgFile = file;
		main = emain;
		this.reload();
		customCfg.options().copyDefaults(true);
	}
	
	public CustomConfig(File file, String cfgName, DeathPay emain)
	{
		cfgDefaultName = cfgName;
		this.construct(file, emain);
	}
	
	public CustomConfig(String filestr, DeathPay emain)
	{
		cfgDefaultName = filestr;
		File file = new File(emain.getDataFolder(), filestr);
		this.construct(file, emain);
	}
	
	public void reload()
	{
		if(!customCfgFile.exists())
		{
			try
			{
				customCfgFile.createNewFile();
			} catch(Exception e)
			{
				return;
			}
		}
		
		customCfg = YamlConfiguration.loadConfiguration(customCfgFile);
		
		InputStream defCfgStream = main.getResource(cfgDefaultName);
		if(defCfgStream != null)
		{
			YamlConfiguration defCfg = YamlConfiguration.loadConfiguration(defCfgStream);
			customCfg.setDefaults(defCfg);
		}
	}
	
	public void save()
	{
		if(customCfg == null || customCfgFile == null)
		{
			return;
		}
		
		try
		{
			customCfg.save(customCfgFile);
		} catch(Exception e)
		{
			
		}
	}
	
	public FileConfiguration getCfg()
	{
		if(customCfg == null)
		{
			this.reload();
		}
		
		return customCfg;
	}
}
