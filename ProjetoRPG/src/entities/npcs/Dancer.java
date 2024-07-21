package entities.npcs;

import java.io.IOException;

import javax.imageio.ImageIO;

import entities.player.Player;
import main.screen.GameScreen;

public class Dancer extends Npc {

	public Dancer(GameScreen gs) {
		super(gs);
		
		super.setX(32*48);
		super.setY(54*48-24);
		
		super.setDialogue(new String[] {"IFH3278RMNG328MCUI2RHM,OIRE,CFOIWEG078G34097tmc,8,rtgueivg4087gm,36t80346W$%&YW$GVYUE$%ÜB%&UIe567ui%E6uiBN%EÜ&IB¨YIUJ6fIYr6n7IbnjhrYÜIH¨&*Ju6iJ67r89j¨&IJnR¨hBiR&6HBVyufIjR76iJN$%76W$%6GV#%TVrgTYnh%YUn7%ÏOnO8*&HBrYSTv$5Yv#4t%VG3YTer7bin6&IN(P(8tmOR¨&b5u$%yVC4wrYvW$ryb4w%ÿU5Ü%6UnuiomuI)<(8p,89pM<&Ünb$5WvbQ34rTbv3Q$tBw35Ye5ÜIR¨&M&T*okm7t*OUIo7t89ot7*OI7Im¨&i¨7inj67iM¨79m68O67uimyuimN¨R7(Mr6789M6r7*(r6&Iv45YC546q34¨$ü76n&¨8Ijmn¨&*9MtYuB35$7b3y#VQ456v3rvweTvey5686n79m70IRüi45cqw!2c123!2v3577089)çO(çmoiçmlkUm n5 bn %ë6352x2x412X1eqwdfCetrytyrbUtyuM&*(70,ini¨&*n67847#$5VBrEtbvERyB56uiM¨&*M76*%M¨8465n$%6B45Y645un67878),*(0<O&*p,oi*7,oi8&MK56um¨YUb4y45Yb4%yb$5y4%&M¨7*m7(&O789o890lOM(,P<L*&omYB#$V@C12#CQWeqwcadsffgBfuM&(7890780¨&NihuionyityunrtyURtUrtBrtuBrtubtrMRYTtYUtyUtYUtYutyutyUtYUtioyuO78O&*k087M67#$523trcd27trn79a26t3597m6cfta43257cfm,ga937456gcf9m87356ghfcm893uht4uoi3s4ghtmv,36t78gf,mk83756tygv,reumioyghosiureygh,ko4ius5yhgb4s087348T7V3M8T7VGY084H7YG4L85HYG485KY0G87K45Y6H78HO6YO4ISY,KG8O4S5Y60KG84Y560897&*tyuinj¨&(njüiyuIJN4TEGTNCVUYERFTC49N37658TFN9MC3AYT8OEX45TXE756YTMPXG98E56YP9G4S5T6YNMOG458Y6M9P8Y589HI67089ÇJ067Ç8094KC938MQ87TM7,Z762FX3BR78NCFB7RT4Q2TGFH8735TGHN83W70T654GBN30W876T4GHN83W7056J8G7W35TJN6W0354GHN84W5N6MJG45Y6HGJ40W568YJGHW56Y40GH4578"});
		super.setHitbox(new int[][] {{0,-48*2+24},{48,-24}});
		super.setLocation("world2");
		this.setSprites();
	}

	@Override
	public void setSprites() {
		try {
			super.setIdleSprites(
				ImageIO.read(getClass().getResourceAsStream("/npcs/dancer/dancer_up.png")), 
				ImageIO.read(getClass().getResourceAsStream("/npcs/dancer/dancer_down.png")), 
				null, 
				null
			);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void action() {
		super.setSprite(super.getIdleSprites()[1]);
	}

	@Override
	public void interaction(Player player) {
		super.setSprite(super.getIdleSprites()[0]);
		super.getGs().setDialogueState();
	}

}
