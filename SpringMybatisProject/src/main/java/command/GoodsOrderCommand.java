package command;

public class GoodsOrderCommand {
   
   String receiverName;
   String purchaseAddr;
   String receiverPhone;
   String purchaseRequest;
   String prodNums;
   String purchaseTotPrice;
   String purchaseMethod;
   
   public String getReceiverName() {
      return receiverName;
   }
   public void setReceiverName(String receiverName) {
      this.receiverName = receiverName;
   }
   public String getPurchaseAddr() {
      return purchaseAddr;
   }
   public void setPurchaseAddr(String purchaseAddr) {
      this.purchaseAddr = purchaseAddr;
   }
   public String getReceiverPhone() {
      return receiverPhone;
   }
   public void setReceiverPhone(String receiverPhone) {
      this.receiverPhone = receiverPhone;
   }
   public String getPurchaseRequest() {
      return purchaseRequest;
   }
   public void setPurchaseRequest(String purchaseRequest) {
      this.purchaseRequest = purchaseRequest;
   }
   public String getProdNums() {
      return prodNums;
   }
   public void setProdNums(String prodNums) {
      this.prodNums = prodNums;
   }
   public String getPurchaseTotPrice() {
      return purchaseTotPrice;
   }
   public void setPurchaseTotPrice(String purchaseTotPrice) {
      this.purchaseTotPrice = purchaseTotPrice;
   }
   public String getPurchaseMethod() {
      return purchaseMethod;
   }
   public void setPurchaseMethod(String purchaseMethod) {
      this.purchaseMethod = purchaseMethod;
   }
}