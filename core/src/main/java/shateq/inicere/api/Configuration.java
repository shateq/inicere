package shateq.inicere.api;

import shateq.inicere.impl.Inicere;

import java.io.File;
import java.io.IOException;

public interface Configuration {
    String charset = "UTF-8";

    Inicere setFile(File file);

    File file();

    Inicere bind(Object obj);

    Object bound();

    <R> R get(String key);

    <S> S set(String key, S value) throws IOException;

    <D> D delete(String key) throws IOException;

    /**
     * Delete a file.
     * @return Deleted filename
     */
    String kill() throws IOException; //filename

    void setReadonly(boolean readonly);

//    // TODO: refactoring
////    private CommentedFileConfig toml() {
////        return CommentedFileConfig.builder(path).autosave().charset(StandardCharsets.UTF_8).build();
////    }
//
//    private void prepareAbsent() {
//        try {
//            if(!Files.exists(path)) {
//                Files.createFile(path);
//            }
//            if(!viable()) {
//                final Set<PosixFilePermission> permissions = Set.of(PosixFilePermission.OWNER_WRITE, PosixFilePermission.OWNER_READ, PosixFilePermission.GROUP_READ, PosixFilePermission.OTHERS_READ);
//                Files.setPosixFilePermissions(path, permissions);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void defaults(Object o) throws IllegalAccessException {
//        Map<Element, Field> elements = getElements(o);
////        final CommentedFileConfig c = toml();
////        c.load();
////
////        for(Element e : elements.keySet()) {
////            String key = e.value();
////            Field field = elements.get(e);
////            field.setAccessible(true);
////
////            if(key.isEmpty()) {
////                key = Key.keyedName(field.getName());
////            }
////
////            c.set(key, field.get(o));
////        }
////        c.close();
//    }
//
//    public void bindValues(Object o) throws IllegalAccessException {
////        Map<Element, Field> elements = getElements(o);
////        final CommentedFileConfig c = toml();
////        c.load();
////
////        for(Element e : elements.keySet()) {
////            String key = e.value();
////            Field field = elements.get(e);
////            field.setAccessible(true);
////
////            if(key.isEmpty()) {
////                key = Key.keyedName(field.getName());
////            }
////
////            field.set(o, c.get(key));
////        }
////        c.close();
//    }
//
//    public boolean viable() {
//        return Files.isReadable(path) && Files.isWritable(path);
//    }
}
