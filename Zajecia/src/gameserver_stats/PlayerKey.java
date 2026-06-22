package gameserver_stats;

/*
Record idealnie nadaje sie na klucz do mapy z racji na to, iż wszystkie pola w recordzie są finalne.
Ich hashcode jest stały, trafiają do danego można je zwrócić.
Record generuje również automatycznie nadpisanie metod equals() i hashcode().
 */

public record PlayerKey(String uuid, String userName) {
}
