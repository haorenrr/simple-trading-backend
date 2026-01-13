package org.example.mylearn.common.util;
import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.List;

public class MixedJsonConverter extends ClassicConverter {
    private static final Gson compactGson = new GsonBuilder().create();

    @Override
    public String convert(ILoggingEvent event) {
        // 1. 先获取格式化后的原始消息 (例如 "查询结果如下：{}")
        // 如果没有参数，message 就是原始字符串；如果有参数，它是占位符替换后的结果
        String message = event.getFormattedMessage();

        // 2. 获取原始参数数组
        Object[] args = event.getArgumentArray();

        // 3. 逻辑判断：
        // 如果没有参数，说明是一条普通的文本日志，直接返回原始消息
        if (args == null || args.length == 0) {
            return message;
        }

        // 4. 如果有参数，我们尝试处理第一个参数（假设它是你要转换的 List 或 Object）
        Object data = args[0];

        // 如果第一个参数是 List，应用你的“混合格式”逻辑
        if (data instanceof List) {
            List<?> list = (List<?>) data;
            StringBuilder sb = new StringBuilder("[\n");
            for (int i = 0; i < list.size(); i++) {
                sb.append("  ").append(compactGson.toJson(list.get(i)));
                if (i < list.size() - 1) sb.append(",\n");
            }
            return sb.append("\n]").toString();
        }

        // 如果不是 List 但又是对象，返回其紧凑 JSON
        // 注意：这里可以根据需求决定，是返回格式化后的 message 还是只返回 data 的 JSON
        return compactGson.toJson(data);
    }
}