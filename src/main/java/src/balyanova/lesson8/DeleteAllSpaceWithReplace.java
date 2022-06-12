package src.balyanova.lesson8;

public class DeleteAllSpaceWithReplace {
    public static void main(String[] args) {

        String space = "Nemo enim ipsam voluptatem, nam libero tempore, cum soluta nobis est eligendi optio, " +
                "cumque nihil impedit, quo minus id, quod maxime placeat, sunt in culpa qui officia deserunt " +
                "mollit anim id est laborum?";

        String withoutSpace = space.replaceAll("\\s", "");
        System.out.println(withoutSpace);
    }
}
