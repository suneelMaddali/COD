package com.example.cod;

public class Result {

    Result2 result;
    public Result()
    {

    }
    public Result(Result2 result) {
        this.result = result;
    }

    class Result2{
        UpLink   uplink_message;

        public Result2()
        {

        }
        public Result2(UpLink uplink_message) {
            this.uplink_message = uplink_message;
        }

        public UpLink getUplink_message() {
            return uplink_message;
        }

        public void setUplink_message(UpLink uplink_message) {
            this.uplink_message = uplink_message;
        }
    }

    static class UpLink{

        DecodedPayLoad decoded_payload;

        public UpLink(){}
        public UpLink(DecodedPayLoad decoded_payload) {
            this.decoded_payload = decoded_payload;
        }

        public DecodedPayLoad getDecoded_payload() {
            return decoded_payload;
        }

        public void setDecoded_payload(DecodedPayLoad decoded_payload) {
            this.decoded_payload = decoded_payload;
        }
    }

    static class DecodedPayLoad{

        Long gassensor;
        Long humidity;
        Long temperature;
        public DecodedPayLoad(){}

        public DecodedPayLoad(Long gassensor, Long humidity, Long temperature) {
            this.gassensor = gassensor;
            this.humidity = humidity;
            this.temperature = temperature;
        }

        public Long getGassensor() {
            return gassensor;
        }

        public void setGassensor(Long gassensor) {
            this.gassensor = gassensor;
        }

        public Long getHumidity() {
            return humidity;
        }

        public void setHumidity(Long humidity) {
            this.humidity = humidity;
        }

        public Long getTemperature() {
            return temperature;
        }

        public void setTemperature(Long temperature) {
            this.temperature = temperature;
        }
    }


    public Result2 getResult() {
        return result;
    }

    public void setResult(Result2 result) {
        this.result = result;
    }
}

