fun main () : nothing

	fun p2 (ref h :int[1]): int;

	fun p3(b: int) :char;
	
	fun pt(b : char) : int;

    fun p2 (ref h : int[1]) : int
        var a : int;

    {
        h <- a + 3;
        h <- p3(a);
		pt(p3(3));
    }

{}
