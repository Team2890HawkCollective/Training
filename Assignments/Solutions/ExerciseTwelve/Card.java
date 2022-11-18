public class Card {
    private enum Rank { ACE, DEUCE, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING };
    private enum Suit { DIAMONDS, CLUBS, HEARTS, SPADES };

    String rank;
    String suit;

    public Card(String rank, String suit) {
        assert isValidRank(rank);
        assert isValidSuit(suit);
        this.rank = rank;
        this.suit = suit;
    }

    public static <E extends Enum<E>> boolean isInEnum(String value, Class<E> enumClass) {
        for (E e : enumClass.getEnumConstants()) {
            if(e.name().equals(value)) { return true; }
        }
        return false;
    }

    public String getSuit() {
        return suit;
    }

    public String getRank() {
        return rank;
    }

    public static boolean isValidRank(String myRank) {
        return isInEnum(myRank, Rank.class);
    }

    public static boolean isValidSuit(String mySuit) {
        return isInEnum(mySuit, Suit.class);
    }

    public static void main(String[] args) {

        Card c1 = new Card("KING", "SPADES");
        System.out.println(c1.getRank() + ":" + isValidRank(c1.getRank()));
        System.out.println(c1.getSuit() + ":" + isValidSuit(c1.getSuit()));

        Card c2 = new Card("JOKER", "BALL");
        System.out.println(c2.getRank() + ":" + isValidRank(c2.getRank()));
        System.out.println(c2.getSuit() + ":" + isValidSuit(c2.getSuit()));

    }
}
