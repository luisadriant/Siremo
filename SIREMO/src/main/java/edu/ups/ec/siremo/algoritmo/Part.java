package edu.ups.ec.siremo.algoritmo;

public class Part {

			public int PartId;
	        public double PartProb;

	        public Part(int id, double prob) { 
	            this.PartId = id;
	            this.PartProb = prob;
	        }
	        
	        
	        public double getPartProb() {
				return PartProb;
			}


			public void setPartProb(double partProb) {
				PartProb = partProb;
			}


			public int getPartId() {
				return PartId;
			}


			public void setPartId(int partId) {
				PartId = partId;
			}


			public String toString()
	        {
	            return "usuario: " + PartId + "   prob: " + PartProb;
	        }

	
}
