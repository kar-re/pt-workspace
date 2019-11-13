package se.lth.cs.pt.window;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.*;

import javax.swing.*;

/**
 * Ett ritfönster, ungefär som det som använts i
 * "Objektorienterad programmering och Java".
 * 
 * @author Per Holm
 * @author Maj Stenmark
 * @author Björn Regnell
 * @author Patrik Persson
 */
public class SimpleWindow {

    public static final int MOUSE_EVENT   = WindowControls.MOUSE_EVENT;
    public static final int KEY_EVENT     = WindowControls.KEY_EVENT;
    public static final int TIMEOUT_EVENT = WindowControls.TIMEOUT_EVENT;

    // Särskilt timeout-värde, som betyder att vi ska vänta utan time-out alls
    private static final long INFINITE_TIMEOUT = -1L;

    // Fönstrets tillstånd
    private int penX        = 0;
    private int penY        = 0;
    private int fontSize    = 14;
    private String fontName = Font.SANS_SERIF;
    private int lineWidth   = 1;

    private static int nbrOpenFrames = 0;

    // Fönstrets ritarea
    private final JPanel panel = new JPanel() {
        private static final long serialVersionUID = 1;

        @Override
        public Dimension getPreferredSize() {
            return dimension;
        }

        @Override
        public void paintComponent(Graphics g) {
            g.drawImage(pixels, 0, 0, this);
        }
    };

    // Swing-komponenter
    private final JFrame frame;
    private final BufferedImage pixels;
    private final Dimension dimension;
    private final Graphics2D pen;
    private final Box inputBox          = Box.createHorizontalBox();
    private final JTextField inputField = new JTextField();
        
    private final EventMonitor event    = new EventMonitor();    

    /**
     * Skapar ett fönster och gör det synligt.
     * 
     * @param width   fönstrets bredd (räknat i pixlar)
     * @param height  fönstrets höjd (räknat i pixlar)
     * @param title   fönstrets titel
     */
    public SimpleWindow(int width, int height, String title) {
        frame = new JFrame(title);
        frame.setResizable(false);
        frame.setLayout(new BorderLayout());

        // När det sista fönstret stängs, avsluta programmet
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                nbrOpenFrames--;
                if (nbrOpenFrames > 0) {
                    frame.setVisible(false);
                    frame.dispose();
                } else {
                    System.exit(0);
                }
            }
        });

        dimension = new Dimension(width, height);
        pixels = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        
        pen = pixels.createGraphics();
        pen.setColor(Color.BLACK);
        pen.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_GASP);
        pen.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
        pen.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        panel.setBackground(Color.WHITE);
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                event.submit(e);
            }
        });
        panel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                event.submit(e);
            }
        });

        frame.getContentPane().add(panel, BorderLayout.CENTER);

        inputBox.add(inputField);
        inputBox.setVisible(false);
        frame.getContentPane().add(inputBox, BorderLayout.SOUTH);
        frame.pack();
        
        clear();
        open();
    }

    /**
     * Tar reda på fönstrets storlek i x-led.
     * 
     * @return fönstrets bredd
     */
    public int getWidth() {
        return dimension.width;
    }

    /**
     * Tar reda på fönstrets storlek i y-led.
     * 
     * @return fönstrets höjd
     */
    public int getHeight() {
        return dimension.height;
    }

    /** Raderar innehållet i fönstret. */
    public void clear() {
        Graphics2D eraser = pixels.createGraphics();
        eraser.setColor(Color.WHITE);
        eraser.fillRect(0, 0, getWidth(), getHeight());
        panel.repaint();
    }

    /** Stänger fönstret tillfälligt. */
    public void close() {
        if (frame.isVisible()) {
            frame.setVisible(false);
            nbrOpenFrames--;
        }
    }

    /** Öppnar ett stängt fönster. */
    public void open() {
        if (!frame.isVisible()) {
            frame.setVisible(true);
            nbrOpenFrames++;
        }
    }

    /**
     * Flyttar pennan till punkten x,y utan att rita.
     * 
     * @param x   x-koordinat för pennans nya position
     * @param y   y-koordinat för pennans nya position
     */
    public void moveTo(int x, int y) {
        penX = x;
        penY = y;
    }

    /**
     * Flyttar pennan till punkten x,y och ritar samtidigt en rät linje.
     * 
     * @param x   x-koordinat för pennans nya position
     * @param y   y-koordinat för pennans nya position
     */
    public void lineTo(int x, int y) {
        pen.setStroke(new BasicStroke(lineWidth, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER));
        pen.drawLine(penX, penY, x, y);
        panel.repaint();
        moveTo(x, y);
    }

    /**
     * Skriver texten txt med början i pennans aktuella läge. Pennans läge påverkas inte.
     * 
     * @param txt   texten som ska skrivas i fönstret
     */
    public void writeText(String txt) {
        pen.setFont(new Font(fontName, Font.PLAIN, fontSize));
        pen.drawString(txt, penX, penY);
        panel.repaint();
    }

    /**
     * Tar reda på x-koordinaten för pennans läge.
     * 
     * @return pennans x-koordinat
     */
    public int getX() {
        return penX;
    }

    /**
     * Tar reda på y-koordinaten för pennans läge.
     * 
     * @return pennans y-koordinat
     */
    public int getY() {
        return penY;
    }

    /**
     * Sätter linjebredden till thickness pixlar
     * 
     * @param thickness   linjebredd (i pixlar, där 1 är normal bredd)
     */
    public void setLineWidth(int thickness) {
        lineWidth = thickness;
    }

    /**
     * Sätter linjefärgen till col. Det finns ett antal inbyggda färger, som
     * Color.RED eller Color.MAGENTA. Man kan även skapa nya, egna färger:
     * 
     * <pre>
     * {@code
     *   SimpleWindow w = new SimpleWindow(300, 300, "colordemo");
     *   w.setLineColor(Color.RED);
     *   w.moveTo(100, 100);
     *   w.lineTo(200, 100);                        // en röd linje
     *   
     *   Color mycolor = new Color(242, 128, 161);  // en helt egen färg
     *   w.setLineColor(mycolor);
     *   w.lineTo(200, 200);                        // en linje i denna färg
     *   ...
     * }
     * </pre>
     * 
     * @param col   den nya linjefärgen
     * 
     * @see <a href="https://docs.oracle.com/javase/8/docs/api/java/awt/Color.html">dokumentation för java.awt.Color</a>
     */
    public void setLineColor(Color col) {
        pen.setColor(col);
    }

    /**
     * Tar reda på linjebredden.
     * 
     * @return linjebredden (i pixlar)
     */
    public int getLineWidth() {
        return lineWidth;
    }

    /**
     * Tar reda på linjefärgen.
     * 
     * @return linjefärg
     */
    public Color getLineColor() {
        return pen.getColor();
    }

    /** Väntar tills användaren har klickat på en musknapp. */
    public void waitForMouseClick() {
        event.await(MouseEvent.class, INFINITE_TIMEOUT);
    }

    /**
     * Tar reda på x-koordinaten för musens position vid senaste musklick.
     * 
     * @return klickets x-koordinat
     */
    public int getMouseX() {
        return event.getMouseX();
    }

    /**
     * Tar reda på y-koordinaten för musens position vid senaste musklick.
     * 
     * @return klickets y-koordinat
     */
    public int getMouseY() {
        return event.getMouseY();
    }

    /**
     * Väntar tills användaren trycker ned en tangent på tangentbordet.
     * 
     * @return den tryckta tangenten
     */
    public char waitForKey() {
        return event.await(KeyEvent.class, INFINITE_TIMEOUT).getKeyChar();
    }

    /**
     * Väntar tills användaren antingen klickar på en musknapp eller trycker ned en
     * tangent på tangentbordet.
     */
    public void waitForEvent() {
        event.await(InputEvent.class, INFINITE_TIMEOUT);
    }

    /**
     * Tar reda på vilket slags händelse som inträffat (MOUSE_EVENT eller KEY_EVENT).
     * 
     * @return KEY_EVENT för tangenttryck, MOUSE_EVENT för musklick
     */
    public int getEventType() {
        return event.getType();
    }

    /**
     * Tar reda på vilken tangent som trycktes ned vid en KEY_EVENT-händelse.
     * 
     * @return teckenkoden för tangenten
     */
    public char getKey() {
        return event.getKey();
    }

    /**
     * Väntar ms millisekunder.
     * 
     * @param ms   antalet millisekunder programmet ska pausa
     */
    public static void delay(int ms) {
        if (ms > 0) {
            try {
                Thread.sleep(ms);
            } catch (InterruptedException e) {
                throw new Error(e);
            }
        }
    }

    /** Hämtar avancerade kontroller för detta fönster. */
    public WindowControls getAdvancedControls() {
        return controls;
    }

    /** @deprecated använd {@link se.lth.cs.pt.window.WindowControls#getAdvancedControls()} */
    @Deprecated
    public void drawImage(Image image) {
        controls.drawImage(image);
    }

    /** @deprecated använd {@link se.lth.cs.pt.window.WindowControls#getAdvancedControls()} */
    @Deprecated
    public void addSprite(Sprite s) {
        controls.addSprite(s);
    }

    // ===================================================================

    // monitor för senast mottagna InputEvent
    public class EventMonitor {
        
        private static final int NO_EVENT      = -1;

        private InputEvent ev = null;
        private int eventType = NO_EVENT;
        private int mouseX    = 0;
        private int mouseY    = 0;
        private char key      = (char) 0;
        
        // Anropas från Swing-tråden
        private synchronized void submit(InputEvent e) {
            ev = e;
            notifyAll();
        }

        // Väntar på ett InputEvent, eller någon av subklasserna MouseEvent/KeyEvent.
        // Anropet blockerar upp till 'timeout' millisekunder. Returvärdet är av
        // samma klass som argumentet c (eller en subklass till c).
        @SuppressWarnings("unchecked")
        private synchronized <E extends InputEvent> E await(Class<E> c, long timeout) {
            try {
                long deadline = System.currentTimeMillis() + timeout;
                SwingUtilities.invokeAndWait(() -> panel.requestFocus());
                do {
                    ev = null;
                    if (timeout == INFINITE_TIMEOUT) {
                        wait();
                    } else {
                        long delay = deadline - System.currentTimeMillis();
                        if (delay > 0) {
                            wait(delay);
                        }
                    }
                } while (ev != null && !c.isInstance(ev));

                if (ev == null) {
                    eventType = TIMEOUT_EVENT;
                } else if (ev instanceof MouseEvent) {
                    eventType = MOUSE_EVENT;
                    mouseX = ((MouseEvent) ev).getX();
                    mouseY = ((MouseEvent) ev).getY();
                } else if (ev instanceof KeyEvent) {
                    eventType = KEY_EVENT;
                    key = ((KeyEvent) ev).getKeyChar();
                } else {
                    throw new Error("unexpected event: " + ev);
                }
                return (E) ev;
            } catch (InterruptedException | InvocationTargetException e) {
                throw new Error(e);
            }
        }

        private synchronized int getType() {
            return eventType;
        }

        private synchronized int getMouseX() {
            return mouseX;
        }

        private synchronized int getMouseY() {
            return mouseY;
        }

        private synchronized char getKey() {
            return key;
        }
    }

    // ===================================================================

    private final WindowControls controls = new WindowControls() {
        
        @Override
        public void setFontSize(int size) {
            fontSize = size;
        }

        @Override
        public void setFontName(String name) {
            fontName = name;
        }

        @Override
        public void addSprite(Sprite s) {
            try {
                SwingUtilities.invokeAndWait(() -> panel.add(s));
            } catch (InvocationTargetException | InterruptedException e) {
                throw new Error(e);
            }
        }

        @Override
        public String input() {
            try {
                final CompletableFuture<String> userInput = new CompletableFuture<>();
                final ActionListener listener = e -> userInput.complete(inputField.getText());
                SwingUtilities.invokeLater(() -> {
                    inputField.setText("");
                    inputField.addActionListener(listener);
                    inputBox.setVisible(true);
                    frame.pack();
                    inputField.requestFocus();
                });
                String s = userInput.get();
                SwingUtilities.invokeLater(() -> {
                    inputBox.setVisible(false);
                    inputField.removeActionListener(listener);
                    frame.pack();
                });
                return s;
            } catch (ExecutionException | InterruptedException e) {
                throw new Error(e);
            }
        }

        @Override
        public int waitForUserInput(long timeout) {
            event.await(InputEvent.class, timeout);
            return event.getType();
        }

        @Override
        public void drawImage(Image image) {
            pen.drawImage(image, penX, penY, panel);
            panel.repaint();
        }
    };
}
