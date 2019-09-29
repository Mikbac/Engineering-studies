{
kApriv = read("kA-priv.txt");

d = kApriv[1];
n = kApriv[2];

print("d = ", kApriv[1]);
print("n = ", kApriv[2]);

/* ---------- */
c = read("c.txt");

print("c= ", c);

/* ---------- */

q = read("q.txt");
p = read("p.txt");

print("q = ", q);
print("p = ", p);

/* ---------- */

cp = lift(Mod(c, p));
cq = lift(Mod(c, q));

dp = lift(Mod(d, p-1));
dq = lift(Mod(d, q-1));

mp = lift(Mod(cp, p)^dp);
mq = lift(Mod(cq, q)^dq);

u = lift(Mod(p, q)^(-1));

M = lift(Mod((mp + p * (mq-mp) * u), u));

print("M (CTR) = ", M);

}