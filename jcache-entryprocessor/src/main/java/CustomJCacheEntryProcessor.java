import javax.cache.processor.EntryProcessor;
import javax.cache.processor.EntryProcessorException;
import javax.cache.processor.MutableEntry;

public class CustomJCacheEntryProcessor implements EntryProcessor {

    @Override
    public Object process(MutableEntry entry, Object... arg) throws EntryProcessorException {
        System.out.println(entry.getValue());
        return entry.getValue();
    }
}
