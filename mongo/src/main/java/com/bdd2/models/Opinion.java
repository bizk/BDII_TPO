package com.bdd2.models;

import java.util.Date;
import java.util.Random;

import com.mongodb.BasicDBObject;

public class Opinion implements DBObjectInterface {
	private String autor;
	private Date date;
	private String currentSituation;
	private String situation;
	private String prediction;
	private String suggestion;

	public Opinion (String autor, Date date, String currentSituation, 
			String situation, String prediction, String suggestion) {
		this.autor = autor;
		this.date = date;
		this.currentSituation = currentSituation;
		this.situation = situation;
		this.prediction = prediction;
		this.suggestion = suggestion;
	}
	
	public Opinion(String autor) {
		this.autor = autor;
		this.date = new Date();
		this.currentSituation = CurrentSituation.generateCurrentSituation();
		this.situation = Situation.generateSituation();
		this.prediction = Predictions.generatePrediction();
		this.suggestion = Sugestions.generateSugestions();
	}
	
	private enum CurrentSituation {
		S1 {
			public String toString() {
				return "We can predict what is going to happen.";
			}
		},
		S2 {
			public String toString() {
				return "We dont know what is going to happen.";
			}
		},
		S3 {
			public String toString() {
				return "The situation is a mess.";
			}
		},
		S4 {
			public String toString() {
				return "The current situation is complex.";
			}
		};
		private static String generateCurrentSituation() {
			Random random = new Random();
			return values()[random.nextInt(values().length)].toString();
		}
	}

	private enum Situation {
		S1 {
			public String toString() {
				return "THe market is going down";
			}
		},
		S2 {
			public String toString() {
				return "The market is going up.";
			}
		},
		S3 {
			public String toString() {
				return "The market situation is awkward.";
			}
		},
		S4 {
			public String toString() {
				return "This market sector is going up.";
			}
		},
		S5 {
			public String toString() {
				return "The market is going down.";
			}
		};
		private static String generateSituation() {
			Random random = new Random();
			return values()[random.nextInt(values().length)].toString();
		}
	}

	private enum Predictions {
		P1 {
			public String toString() {
				return "We may loose money.";
			}
		},
		P2 {
			public String toString() {
				return "We may earn a lot.";
			}
		},
		P3 {
			public String toString() {
				return "We will earn a lot.";
			}
		},
		P4 {
			public String toString() {
				return "We will try not to lose a lot.";
			}
		},
		P5 {
			public String toString() {
				return "Lets go newels!.";
			}
		},
		P6 {
			public String toString() {
				return "Our situation wont change much.";
			}
		};

		private static String generatePrediction() {
			Random random = new Random();
			return values()[random.nextInt(values().length)].toString();
		}
	}

	private enum Sugestions {
		Buy, Sell;

		private static String generateSugestions() {
			Random random = new Random();
			return values()[random.nextInt(values().length)].toString();
		}
	}

	@Override
	public BasicDBObject getBasicDBObject() {
		return new BasicDBObject("autor", this.autor).append("date", this.date).append("currentSituation", this.currentSituation)
				.append("situation", this.situation).append("prediction", this.prediction).append("suggestion", this.suggestion);
	}
}
