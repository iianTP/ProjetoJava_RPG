package entities.npcs;

import java.io.IOException;

import javax.imageio.ImageIO;

import entities.player.Player;
import main.screen.GameScreen;

public class Glitch extends Npc {
	
	private Player player;
	private Npc[] npcs;

	public Glitch(GameScreen gs, Player player, Npc[] npcs) {
		super(gs);
		super.setX(45*48);
		super.setY(33*48);
		super.setWalkSpeed(5);
		super.setLocation("world2");
		this.setSprites();
		
		super.setDirection("down");
		
		super.setDialogue(new String[] {"WIERMG072813$¨1346134Tf#^>`}>:`?>O`^UN%Ü#B%¨&)VB(%¨$YJ*V($%JYNV_()C$N%YG(V*$N@%¨(*&GH%$Y)&WH)(HG¨$WH%)¨(GH$S)(%¨GHW)$(¨J%)W$(%¨HW$G%&G$%¨JW$(%&NG)$W%&*()GN$)(%WYJG()$%YWK<G(BI¨%ULG¨)I¨&I)&*IÇ)ÇM*&)OIÇ*MO_*&OÇ_)M&OÇM_IO_¨Ç*IO_IÇ&PI_Ç%&I_PP%L¨*()$L%¨B&)(#$K¨%)*@X@#JE*(JZS(IM!(*@EMJZ!KSE)IKXO$)@K%)OC#$K%¨)(#$6ci3906cj5439068cjh4390658y345634h6439637693709637495739594534t/3c4tC#$IT(MC#(T_$CV_(YM($IBV%Ü,5d6i-9dbnlç6u.-9wb87bw465BVW_(W&)U¨*_(MW¨_(V_(*WM$_)YIB$U`BW$oUB`W$Ü`BW$`¨UB<¨$U<¨B$IUW(¨_MUB¨*_TY-uobj49-5y8wmy60598y4m5n087xg028735.diu4t3h05897$%¨&(*W%$&*W(%&W)$%(&$W(%&(N%$Y(WMYOIVYPVYUKJGHjmghojmiUBIUBG,UAEBCFEABTUICFB673G49856C7MG,4395CMYWER98T9AE87TLH98VAERHTYL9VERHTKY89V4KH5Y54¨&%$v¨$$%¨$%¨45¨$&*#¨(#*¨v39VHFEAT8HEV7HS9MHY98V5S4GY98V7,Y598HXERIYH,XOIRTYH,O8S4576YM845H,6VEX65H34E86YE8Z7H6846#&#¨#¨#(*¨(*#¨#*¨V8URTHMVSE8TRHXEKHTMVUIY36#&#¨&ICVEHTM8HRUITCHXIMTH,CMERYUTVB$%&$%(*&($*&SERTHJUVISYHRTE,UISEBR,TUYVEB.YTOIERY54&$%&$%&)($%&()%0YUKROXP;ÇYXR~RXTJ~XRURXTUXRT]R[][][~UÇRTU~RTKUÇXRTUXR{}{}{XRTYOXJ,YIOUHROYIUH4OIYHIU9834H68H734869BP9E,6GV089EB46089XBGE,06HERTY,BOÇI5EJ79BH,YXIHY09BEIUHNYIU45UYB45y$%&%$¨$%¨$&$%84597822!@#!$@%(#)&(%¨*)¨&*()%&K3YT,EIUOYHB XVDKLJYBV,XEOYB,VIOEZRYBVIOUEBRYIOUVEZBRYOIEVXBYIURHNYOJOPEI5YJH,09456H8J,9R8XTYHIFCYJÇLXRÇYIH,830576HEZRNTY,PIAW4HUTCF087A3G4TMCFUITHEPITV,H958E7745&$%&$%&)#$(%*@(*¨#*(4J6893477$%()nmu()ü056UNKUOIGMU09856&*%¨907I4560904BI5079HU4B09UH4967uunhu9rtn8aug46AERYBERYNESVYTVESNYIOXYBPCRTUYPNRTUNBRUNPDRUdPYT*BHKBE%EV$¨#SRTHB%&B$¨W&B¨%*IBNE%(NBE%¨*NBTUT¨*BW$%&VC#$%&BV¨%(EHB&)&%IB$%YBV%YVQ#Y#%$YVGQ%#&GVW%&VW$#%&VB*I&¨*(&*(NJ*)NJ(*NB*B#%BHQ!!@#!B@%#SBYRTUNO<O(>:(P`:{:>`)>_`)}>!4T!f#$¨¨3RGM,203987R,09845YGUGE586E56790IU0I,ME50-GU,OUIHSD,PHSBC,helpmePH#$$%v$#!v$%¨@#$vq345"});
		
		this.player = player;
		this.npcs = npcs;
	}

	@Override
	public void setSprites() {
		try {
			super.setSprite(ImageIO.read(getClass().getResourceAsStream("/npcs/glitch.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void action() {
		if (this.player.getLocation().equals(super.getLocation())) {
			int randInt = super.rng(100,1);
			
			if (randInt <= 20) {
				super.setDirection("up");
				super.setWalking(true);
			} else if (randInt > 20 && randInt <= 40) {
				super.setDirection("down");
				super.setWalking(true);
			} else if (randInt > 40 && randInt <= 60) {
				super.setDirection("left");
				super.setWalking(true);
			} else if (randInt > 60 && randInt <= 80) {
				super.setDirection("right");
				super.setWalking(true);
			} else if (randInt > 80 && randInt <= 100) {
				super.setWalking(false);
			}
			
			super.setCollision(false);
			super.getCollision().checkTile(this);
			super.getCollision().checkNpc(this, npcs);
			super.getCollision().checkPlayer(this, player);
			
			if (!super.isColliding() && super.isWalking()) {
				if (super.getDirection().equals("up")) {
					super.goUp();
				} else if (super.getDirection().equals("down")) {
					super.goDown();
				} else if (super.getDirection().equals("left")) {
					super.goLeft();
				} else if (super.getDirection().equals("right")) {
					super.goRight();
				}
			}
		}
	}
	
	@Override
	public void interaction(Player player) {
		super.getGs().setDialogueState();
	}

}
