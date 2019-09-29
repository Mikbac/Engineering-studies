{
default(parisize, 120000000);
setrand(getwalltime());


H = read("Mh.out");
/*M = read("M");*/

data = read("kA-priv.out");
data = read("KA-pub.out");

print("x= ", x);
print("p= ", p);
print("g= ", g);
print("q= ", q);
print("y= ", y);
print("H= ", H);


/*-1-*/
print("krok-1");

k = random();
while(k<1 || k>q,
k = random();
);

print("k= ", k);

/*-2-*/
print("krok-2");

mod1 = Mod(g, p)^k;
mod2 = lift(mod1);

r = Mod(mod2, q);


/*-3-*/
print("krok-3");

s =  Mod(k^(-1) * (H + x * r), q);

print("r= ", r);
print("s= ", s);

/*-4-*/
print("krok-4");

write("rs.out", "r=", lift(r));
write("rs.out", "s=", lift(s));
}