clear all

x0 = [-1;1];

function f = func(x)
    f = -(-abs(x(1) * x(2)) - (x(2))^2);
end

op = optimset('PlotFcns',@optimplotfval);

[x,fval,exitflag,output] = fminsearch(@func,x0,op)
