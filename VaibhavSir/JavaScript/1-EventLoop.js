/*
🔁 Event Loop – Easy Explanation:

JavaScript ek time pe sirf ek kaam karta hai — iska matlab wo single-threaded hai.
Toh agar koi kaam zyada time le (jaise API se data lana, ya setTimeout), toh wo directly handle nahi karta — warna baki kaam ruk jayein. Isliye wo kaam Web APIs ko de diya jata hai (jo browser ya Node.js handle karta hai), aur JavaScript apna kaam karta rehta hai.

👨‍🍳 Event Loop ke Components – Halke Phulke Examples ke Saath:
1. Main Stack (a.k.a. Call Stack)
Yeh woh jagah hai jahan JS ka main code chalta hai — line by line.
📦 Jaise:
console.log("1");

Yeh turant stack pe jaake run ho gaya.

2. Web APIs
Yeh browser ke paas hoti hain — jaise:

setTimeout
fetch
DOM events (click etc.)
Yeh sab asynchronous hota hai.

📦 Jaise:
setTimeout(() => {
  console.log("Timeout done");
}, 1000);

Yeh direct run nahi hota. Browser ke paas chala jata hai, aur 1 second baad callback queue mein chala jata hai.

3. Callback Queue / Task Queue / Macro Task Queue
Yahan woh functions aate hain jo Web APIs ke kaam complete hone ke baad execute karne hain.
📦 Example:

setTimeout
setInterval
fetch ke .then (kabhi kabhi — zyada neeche microtasks mein hota hai)

4. Microtask Queue
Yeh priority wali queue hai — yeh pehle chalayegi callback queue se.
📦 Example:

Promise.then
MutationObserver
Promise.resolve().then(() => {
  console.log("Microtask done");
});

Yeh microtask queue mein jata hai aur call stack khali hote hi turant chala jata hai.

5. Event Loop
Yeh ek nigrani rakhne wala guard hai. Jaise hi call stack khali hota hai, yeh check karta hai:
Pehle microtasks queue mein kuch hai? Agar haan, sab chala do.
Phir callback queue check karta hai — aur pehla kaam uthake stack mein bhejta hai.

🔁 Aasan Example (Step by Step):
*/
console.log("Start");

setTimeout(() => {
  console.log("Timeout");
}, 0);

Promise.resolve().then(() => {
  console.log("Promise done");
});

console.log("End");


/*
Output:

Start
End
Promise done
Timeout

Kyun?

console.log("Start") → Stack pe → run
setTimeout(...) → Web API ko de diya → 0ms baad callback queue mein gaya
Promise.then(...) → microtask queue mein gaya
console.log("End") → Stack pe → run
Stack ab khali → Event loop check karega:
Microtask queue → Promise done → run
Callback queue → Timeout → run

📌 Summary:
Component	        Kya karta hai?
Call Stack	      JavaScript code line by line run karta hai
Web APIs	        Asynchronous kaam handle karta hai (e.g. setTimeout)
Callback Queue	  Web API ke baad callbacks yahaan aate hain
Microtask Queue	  Promises waghera sabse pehle yahin jaate hain
Event Loop	      Stack khali hone ke baad queues check karta hai
*/