package no.kevin.innlevering2.net.packets;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import no.kevin.innlevering2.net.Packet;
import no.kevin.innlevering2.net.PacketHandler;

import java.io.IOException;
import java.nio.ByteBuffer;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class QuestionPacket implements Packet {
    private int questionId;
    private String question;
    private String allowedAnswersRegex;

    @Override
    public void read(ByteBuffer in) throws IOException {
        questionId = in.getInt();
        question = readString(in);
        allowedAnswersRegex = readString(in);
    }

    @Override
    public void write(ByteBuffer out) throws IOException {
        out.putInt(questionId);
        writeString(question, out);
        writeString(allowedAnswersRegex, out);
    }

    @Override
    public void handle(PacketHandler handler) throws IOException {
        handler.handle(this);
    }
}
