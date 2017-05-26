fun main () : nothing
    var x : char;
    var y : int;
    fun p1 (b : char; ref c : int) : int;

    fun p1 (c : char; ref c : int) : int
    {
        return 3;
    }

{
    $y <- p1(x, y);
}
