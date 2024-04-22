clear all

x0 = [0,0];

function f = func(x)
    f = -20*x(1) - 26*x(2) - 4*x(1)*x(2) + 4*(x(1)^2) + 3*(x(2))^2;
end

op = optimset('HessUpdate','dfp','Display','iter');

[x,fval,exitflag,output] = fminunc(@func,x0,op)
