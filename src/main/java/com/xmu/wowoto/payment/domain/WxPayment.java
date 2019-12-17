package com.xmu.wowoto.payment.domain;

public class WxPayment {

    private String prepayId;
    private Payment payment;

    public String getPrepayId() {
        return this.prepayId;
    }

    public Payment getPayment() {
        return this.payment;
    }

    public void setPrepayId(String prepayId) {
        this.prepayId = prepayId;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof WxPayment)) return false;
        final WxPayment other = (WxPayment) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$prepayId = this.getPrepayId();
        final Object other$prepayId = other.getPrepayId();
        if (this$prepayId == null ? other$prepayId != null : !this$prepayId.equals(other$prepayId)) return false;
        final Object this$payment = this.getPayment();
        final Object other$payment = other.getPayment();
        if (this$payment == null ? other$payment != null : !this$payment.equals(other$payment)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof WxPayment;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $prepayId = this.getPrepayId();
        result = result * PRIME + ($prepayId == null ? 43 : $prepayId.hashCode());
        final Object $payment = this.getPayment();
        result = result * PRIME + ($payment == null ? 43 : $payment.hashCode());
        return result;
    }

    public String toString() {
        return "WxPayment(prepayId=" + this.getPrepayId() + ", payment=" + this.getPayment() + ")";
    }
}
