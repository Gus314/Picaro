package ui;

import entities.Player;

import java.awt.GridLayout;
import javax.swing.*;

public class Stats extends JPanel
{	
	private Player player;
	private JLabel hp;
	private JLabel pp;
	private JLabel mp;
	private JLabel minDamage;
	private JLabel maxDamage;
	private JLabel critChance;
	private JLabel defense;
	private JLabel blockChance;
	private JLabel absorbChance;
	private JLabel intelligence;
	private JLabel magicDefense;
	private JLabel exp;
	private JLabel level;
	
	public Stats(Player inPlayer)
	{
		super();
		GridLayout grid = new GridLayout(5, 6);
		this.setLayout(grid);
		player = inPlayer;
		this.add(new JLabel("HP="));
		hp = new JLabel(((Integer)(player.getLife())).toString() + "/" + ((Integer)(player.getMaxLife())).toString() );
		this.add(hp);
		this.add(new JLabel("PP="));
		pp = new JLabel(((Integer)(player.getPhysicalPoints())).toString() + "/" + ((Integer)(player.getMaxPhysicalPoints())).toString());
		this.add(pp);
		this.add(new JLabel("MP="));
		mp = new JLabel(((Integer)(player.getMagicPoints())).toString() + "/" + ((Integer)(player.getMaxMagicPoints())).toString());
		this.add(mp);
		this.add(new JLabel("Min Damage="));
		minDamage = new JLabel(((Integer)(player.getMinDamage())).toString());
		this.add(minDamage);
		this.add(new JLabel("Max Damage="));
		maxDamage = new JLabel(((Integer)(player.getMaxDamage())).toString());
		this.add(maxDamage);
		this.add(new JLabel("Crit Chance="));
		critChance = new JLabel(((Integer)(player.getCritChance())).toString());
		this.add(critChance);
		this.add(new JLabel("Defense="));
		defense = new JLabel(((Integer)(player.getDefense())).toString());
		this.add(defense);
		this.add(new JLabel("Block Chance="));
		blockChance = new JLabel(((Integer)(player.getBlockChance())).toString());
		this.add(blockChance);
		this.add(new JLabel("Absorb Chance="));
		absorbChance = new JLabel(((Integer)(player.getAbsorbChance())).toString());
		this.add(absorbChance);
		this.add(new JLabel("Intelligence="));
		intelligence = new JLabel(((Integer)(player.getIntelligence())).toString());
		this.add(intelligence);
		this.add(new JLabel("Magic Defense="));
		magicDefense = new JLabel(((Integer)(player.getMagicDefense())).toString());
		this.add(magicDefense);
		this.add(new JLabel("Exp="));
		exp = new JLabel(((Integer)(player.getExp())).toString());
		this.add(exp);
		this.add(new JLabel("Level="));
		level = new JLabel(((Integer)(player.getLevel())).toString());
		this.add(level);
	}

	public void refresh()
	{
		hp.setText(((Integer)(player.getLife())).toString() + "/" + ((Integer)(player.getMaxLife())).toString());
		pp.setText(((Integer)(player.getPhysicalPoints())).toString() + "/" + ((Integer)(player.getMaxPhysicalPoints())).toString());
		mp.setText(((Integer)(player.getMagicPoints())).toString() + "/" + ((Integer)(player.getMaxMagicPoints())).toString());
		minDamage.setText(((Integer)(player.getMinDamage())).toString());
		maxDamage.setText(((Integer)(player.getMaxDamage())).toString());
		critChance.setText(((Integer)(player.getCritChance())).toString());
		defense.setText(((Integer)(player.getDefense())).toString());
		blockChance.setText(((Integer)(player.getBlockChance())).toString());
		absorbChance.setText(((Integer)(player.getAbsorbChance())).toString());
		exp.setText(((Integer)(player.getExp())).toString());
		level.setText(((Integer)(player.getLevel())).toString());
		intelligence.setText(((Integer)(player.getIntelligence())).toString());
		magicDefense.setText(((Integer)(player.getMagicDefense())).toString());
	}
}
