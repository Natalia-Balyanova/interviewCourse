package src.balyanova.lesson8;

public class DeleteAllSpaceWithoutReplaceMethod {
    public static void main(String[] args) {

        String space = "Nemo enim ipsam voluptatem, nam libero tempore, cum soluta nobis est eligendi optio, " +
                "cumque nihil impedit, quo minus id, quod maxime placeat, sunt in culpa qui officia deserunt " +
                "mollit anim id est laborum?";

        char[] array = space.toCharArray();

        StringBuffer withoutSpace = new StringBuffer();

        for (char c : array) {
            if ((c != ' ') && (c != '\t')) {
                withoutSpace.append(c);
            }
        }
        System.out.println(withoutSpace);
    }
}
