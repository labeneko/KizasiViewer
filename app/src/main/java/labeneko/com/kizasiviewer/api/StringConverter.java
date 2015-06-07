package labeneko.com.kizasiviewer.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;

import retrofit.converter.ConversionException;
import retrofit.converter.Converter;
import retrofit.mime.TypedInput;
import retrofit.mime.TypedOutput;

public class StringConverter implements Converter {
    public static final String LINE_SEPARATOR = System.getProperty("line.separator");

    @Override
    public Object fromBody(TypedInput body, Type type) throws ConversionException{
        StringBuilder result = new StringBuilder();
        try  {
            BufferedReader reader = new BufferedReader(new InputStreamReader(body.in()));
            while (true) {
                String line = reader.readLine();
                if (line == null) {
                    break;
                }
                result.append(line).append(LINE_SEPARATOR);
            }
        } catch (IOException e) {
            // 何もしない
        }
        return result.toString();
    }

    @Override
    public TypedOutput toBody(Object object) {
        return null;
    }
}