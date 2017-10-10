package com.gcsun.chat;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.websocket.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;

/**
 * Created by 11981 on 2017/9/28.
 * 对ChatMessage进行解码和编码
 */
public class ChatMessageCodec implements Encoder.BinaryStream<ChatMessage>,
        Decoder.BinaryStream<ChatMessage>{
    private static final ObjectMapper MAPPER = new ObjectMapper();
    static {
        MAPPER.findAndRegisterModules();
        MAPPER.configure(JsonGenerator.Feature.AUTO_CLOSE_TARGET, false);
    }

    @Override
    public void encode(ChatMessage chatMessage, OutputStream outputStream) throws
            EncodeException, IOException{
        try {
            //将内容转化为json格式并指定到输出流
            ChatMessageCodec.MAPPER.writeValue(outputStream, chatMessage);

        }catch (JsonGenerationException | JsonMappingException e){
            throw new EncodeException(chatMessage, e.getMessage());
        }

    }

    @Override
    //反序列化JSON ChatMessage
    public ChatMessage decode(InputStream inputStream) throws
            DecodeException, IOException{
        try {
            //读取inputStream流中的内容，转化成对应的ChatMessage类型的对象
            return ChatMessageCodec.MAPPER.readValue(
                    inputStream, ChatMessage.class
            );
        }catch (JsonParseException | JsonMappingException e){
            throw new DecodeException((ByteBuffer) null, e.getMessage());
        }
    }
    @Override
    public void init(EndpointConfig endpointConfig) {}

    @Override
    public void destroy(){}
}
